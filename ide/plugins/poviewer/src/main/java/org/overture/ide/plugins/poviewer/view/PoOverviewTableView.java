package org.overture.ide.plugins.poviewer.view;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.overture.ide.plugins.poviewer.Activator;
import org.overture.ide.plugins.poviewer.PoviewerPluginConstants;
import org.overture.ide.utility.FileUtility;
import org.overture.ide.utility.ProjectUtility;
import org.overturetool.vdmj.pog.POStatus;
import org.overturetool.vdmj.pog.ProofObligation;

public class PoOverviewTableView extends ViewPart implements ISelectionListener
{

	private TableViewer viewer;
	private Action doubleClickAction;
	final Display display = Display.getCurrent();
	private IProject project;
	
	private ViewerFilter provedFilter = new ViewerFilter() {

		@Override
		public boolean select(Viewer viewer, Object parentElement,
				Object element) {
			if (element instanceof ProofObligation
					&& ((ProofObligation) element).status == POStatus.UNPROVED)
				return true;
			else
				return false;
		}

	};
	private Action actionSetProvedFilter;

	class ViewContentProvider implements IStructuredContentProvider
	{
		public void inputChanged(Viewer v, Object oldInput, Object newInput)
		{
		}

		public void dispose()
		{
		}

		@SuppressWarnings("unchecked")
		public Object[] getElements(Object inputElement)
		{
			if (inputElement instanceof List)
			{
				List list = (List) inputElement;
				return list.toArray();
			}
			return new Object[0];
		}

	}

	class ViewLabelProvider extends LabelProvider implements
			ITableLabelProvider
	{

		public void resetCounter()
		{
			count = 0;
		}

		private Integer count = 0;

		public String getColumnText(Object element, int columnIndex)
		{
			ProofObligation data = (ProofObligation) element;
			String columnText;
			switch (columnIndex)
			{
			case 0:
				count++;
				columnText = count.toString();
				break;
			case 1:
				if (!data.location.module.equals("DEFAULT"))
					columnText = data.location.module + "`" + data.name;
				else
					columnText = data.name;
				break;
			case 2:
				columnText = data.kind.toString();
				break;
			case 3:
				columnText ="";//data.status.toString();
				break;
			default:
				columnText = "not set";
			}
			return columnText;

		}

		public Image getColumnImage(Object obj, int index)
		{
			if (index == 3)
			{
				return getImage(obj);
			}
			return null;
		}

		@Override
		public Image getImage(Object obj)
		{
			ProofObligation data = (ProofObligation) obj;

			String imgPath = "icons/cview16/unproved.png";

			if (data.status == POStatus.PROVED)
				imgPath = "icons/cview16/proved.png";
			else if (data.status == POStatus.TRIVIAL)
				imgPath = "icons/cview16/trivial.png";

			return Activator.getImageDescriptor(imgPath).createImage();
		}

	}

	class IdSorter extends ViewerSorter
	{
	}

	/**
	 * The constructor.
	 */
	public PoOverviewTableView() {
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	@Override
	public void createPartControl(Composite parent)
	{
		viewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.H_SCROLL
				| SWT.V_SCROLL);
		// test setup columns...
		TableLayout layout = new TableLayout();
		layout.addColumnData(new ColumnWeightData(20, 20, true));
		layout.addColumnData(new ColumnWeightData(100, 40, true));
		layout.addColumnData(new ColumnWeightData(60, 35, false));
		layout.addColumnData(new ColumnWeightData(20, 20, false));
		viewer.getTable().setLayout(layout);
		viewer.getTable().setLinesVisible(true);
		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setSortDirection(SWT.NONE);
		viewer.setSorter(null);

		TableColumn column01 = new TableColumn(viewer.getTable(), SWT.LEFT);
		column01.setText("Count");
		column01.setToolTipText("Count");

		TableColumn column = new TableColumn(viewer.getTable(), SWT.LEFT);
		column.setText("PO Name");
		column.setToolTipText("PO Name");

		TableColumn column2 = new TableColumn(viewer.getTable(), SWT.LEFT);
		column2.setText("Type");
		column2.setToolTipText("Show Type");

		TableColumn column3 = new TableColumn(viewer.getTable(), SWT.CENTER);
		column3.setText("Status");
		column3.setToolTipText("Show status");

		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());

		makeActions();
		contributeToActionBars();
		hookDoubleClickAction();

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event)
			{

				Object first = ((IStructuredSelection) event.getSelection()).getFirstElement();
				if (first instanceof ProofObligation)
				{
					try
					{
						IViewPart v = getSite().getPage()
								.showView(PoviewerPluginConstants.PoTableViewId);

						if (v instanceof PoTableView)
							((PoTableView) v).setDataList(project,
									(ProofObligation) first);
					} catch (PartInitException e)
					{

						e.printStackTrace();
					}
				}

			}
		});
	}
	
	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		
		fillLocalToolBar(bars.getToolBarManager());
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {

		manager.add(actionSetProvedFilter);
		
		//drillDownAdapter.addNavigationActions(manager);
	}

	private void makeActions()
	{
		doubleClickAction = new Action() {
			@Override
			public void run()
			{
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				if (obj instanceof ProofObligation)
				{
					gotoDefinition((ProofObligation) obj);
					// showMessage(((ProofObligation) obj).toString());
				}
			}

			private void gotoDefinition(ProofObligation po)
			{
				IFile file = ProjectUtility.findIFile(project, po.location.file);
				FileUtility.gotoLocation(file, po.location, po.name);

			}
		};
		
		actionSetProvedFilter = new Action("Filter proved",Action.AS_CHECK_BOX) {
			@Override
			public void run() {
				ViewerFilter[] filters = viewer.getFilters();
				boolean isSet = false;
				for (ViewerFilter viewerFilter : filters) {
					if (viewerFilter.equals(provedFilter))
						isSet = true;
				}
				if (isSet) {
					viewer.removeFilter(provedFilter);
					
				} else {
					viewer.addFilter(provedFilter);
					
				}
				if (viewer.getLabelProvider() instanceof ViewLabelProvider)
					((ViewLabelProvider) viewer.getLabelProvider()).resetCounter(); // this
																					// is
																					// needed
																					// to
																					// reset
																					// the
				// numbering
				viewer.refresh();
			}

		};
	
	}

	private void hookDoubleClickAction()
	{
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event)
			{
				doubleClickAction.run();
			}
		});
	}

	// private void showMessage(String message)
	// {
	// MessageDialog.openInformation(
	// viewer.getControl().getShell(),
	// "PO Test",
	// message);
	// }

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus()
	{
		viewer.getControl().setFocus();
	}

	public void selectionChanged(IWorkbenchPart part, ISelection selection)
	{

		if (selection instanceof IStructuredSelection
				&& part instanceof PoOverviewTableView)
		{
			Object first = ((IStructuredSelection) selection).getFirstElement();
			if (first instanceof ProofObligation)
			{
				try
				{
					IViewPart v = part.getSite()
							.getPage()
							.showView("org.overture.ide.plugins.poviewer.views.PoTableView");

					if (v instanceof PoTableView)
						((PoTableView) v).setDataList(project,
								(ProofObligation) first);
				} catch (PartInitException e)
				{

					e.printStackTrace();
				}
			}
		}

	}

	public void refreshList()
	{
		display.asyncExec(new Runnable() {

			public void run()
			{
				viewer.refresh();
			}

		});
	}

	public void setDataList(final IProject project,
			final List<ProofObligation> data)
	{
		this.project = project;
		display.asyncExec(new Runnable() {

			public void run()
			{
				if (viewer.getLabelProvider() instanceof ViewLabelProvider)
					((ViewLabelProvider) viewer.getLabelProvider()).resetCounter(); // this
																					// is
																					// needed
																					// to
																					// reset
																					// the
				// numbering

				viewer.setInput(data);
			}

		});
	}
}
