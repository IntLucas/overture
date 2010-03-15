package org.overture.ide.core;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.jobs.ISchedulingRule;


public interface IVdmElement extends IAdaptable {

	/**
	 * Constant representing a Java model (workspace level object).
	 * A Java element with this type can be safely cast to {@link IJavaModel}.
	 */
	int VDM_MODEL = 1;

	/**
	 * Constant representing a Java project.
	 * A Java element with this type can be safely cast to {@link IJavaProject}.
	 */
	int VDM_PROJECT = 2;

//	/**
//	 * Constant representing a package fragment root.
//	 * A Java element with this type can be safely cast to {@link IPackageFragmentRoot}.
//	 */
//	int PACKAGE_FRAGMENT_ROOT = 3;
//
//	/**
//	 * Constant representing a package fragment.
//	 * A Java element with this type can be safely cast to {@link IPackageFragment}.
//	 */
//	int PACKAGE_FRAGMENT = 4;

	/**
	 * Constant representing a Java compilation unit.
	 * A Java element with this type can be safely cast to {@link ICompilationUnit}.
	 */
	int COMPILATION_UNIT = 5;

//	/**
//	 * Constant representing a class file.
//	 * A Java element with this type can be safely cast to {@link IClassFile}.
//	 */
//	int CLASS_FILE = 6;

	/**
	 * Constant representing a type (a class or interface).
	 * A Java element with this type can be safely cast to {@link IType}.
	 */
	int TYPE = 7;

	/**
	 * Constant representing a field.
	 * A Java element with this type can be safely cast to {@link IField}.
	 */
	int FIELD = 8;

	/**
	 * Constant representing a method or constructor.
	 * A Java element with this type can be safely cast to {@link IMethod}.
	 */
	int METHOD = 9;

	/**
	 * Constant representing a stand-alone instance or class initializer.
	 * A Java element with this type can be safely cast to {@link IInitializer}.
	 */
	int INITIALIZER = 10;

//	/**
//	 * Constant representing a package declaration within a compilation unit.
//	 * A Java element with this type can be safely cast to {@link IPackageDeclaration}.
//	 */
//	int PACKAGE_DECLARATION = 11;

//	/**
//	 * Constant representing all import declarations within a compilation unit.
//	 * A Java element with this type can be safely cast to {@link IImportContainer}.
//	 */
//	int IMPORT_CONTAINER = 12;
//
//	/**
//	 * Constant representing an import declaration within a compilation unit.
//	 * A Java element with this type can be safely cast to {@link IImportDeclaration}.
//	 */
//	int IMPORT_DECLARATION = 13;

	/**
	 * Constant representing a local variable declaration.
	 * A Java element with this type can be safely cast to {@link ILocalVariable}.
	 * @since 3.0
	 */
	int LOCAL_VARIABLE = 14;

	/**
	 * Constant representing a type parameter declaration.
	 * A Java element with this type can be safely cast to {@link ITypeParameter}.
	 * @since 3.1
	 */
	int TYPE_PARAMETER = 15;

	/**
	 * Constant representing an annotation.
	 * A Java element with this type can be safely cast to {@link IAnnotation}.
	 * @since 3.4
	 */
	int ANNOTATION = 16;

	/**
	 * Returns whether this Java element exists in the model.
	 * <p>
	 * Java elements are handle objects that may or may not be backed by an
	 * actual element. Java elements that are backed by an actual element are
	 * said to "exist", and this method returns <code>true</code>. For Java
	 * elements that are not working copies, it is always the case that if the
	 * element exists, then its parent also exists (provided it has one) and
	 * includes the element as one of its children. It is therefore possible
	 * to navigated to any existing Java element from the root of the Java model
	 * along a chain of existing Java elements. On the other hand, working
	 * copies are said to exist until they are destroyed (with
	 * <code>IWorkingCopy.destroy</code>). Unlike regular Java elements, a
	 * working copy never shows up among the children of its parent element
	 * (which may or may not exist).
	 * </p>
	 *
	 * @return <code>true</code> if this element exists in the Java model, and
	 * <code>false</code> if this element does not exist
	 */
	boolean exists();

	/**
	 * Returns the first ancestor of this Java element that has the given type.
	 * Returns <code>null</code> if no such an ancestor can be found.
	 * This is a handle-only method.
	 *
	 * @param ancestorType the given type
	 * @return the first ancestor of this Java element that has the given type, null if no such an ancestor can be found
	 * @since 2.0
	 */
	IVdmElement getAncestor(int ancestorType);

	

	/**
	 * Returns the resource that corresponds directly to this element,
	 * or <code>null</code> if there is no resource that corresponds to
	 * this element.
	 * <p>
	 * For example, the corresponding resource for an <code>ICompilationUnit</code>
	 * is its underlying <code>IFile</code>. The corresponding resource for
	 * an <code>IPackageFragment</code> that is not contained in an archive
	 * is its underlying <code>IFolder</code>. An <code>IPackageFragment</code>
	 * contained in an archive has no corresponding resource. Similarly, there
	 * are no corresponding resources for <code>IMethods</code>,
	 * <code>IFields</code>, etc.
	 * <p>
	 *
	 * @return the corresponding resource, or <code>null</code> if none
	 * @exception JavaModelException if this element does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 */
	IResource getCorrespondingResource() throws VdmModelException;

	/**
	 * Returns the name of this element. This is a handle-only method.
	 *
	 * @return the element name
	 */
	String getElementName();

	/**
	 * Returns this element's kind encoded as an integer.
	 * This is a handle-only method.
	 *
	 * @return the kind of element; one of the constants declared in
	 *   <code>IJavaElement</code>
	 * @see IJavaElement
	 */
	int getElementType();

	/**
	 * Returns a string representation of this element handle. The format of
	 * the string is not specified; however, the identifier is stable across
	 * workspace sessions, and can be used to recreate this handle via the
	 * <code>JavaCore.create(String)</code> method.
	 *
	 * @return the string handle identifier
	 * @see JavaCore#create(java.lang.String)
	 */
	String getHandleIdentifier();

	/**
	 * Returns the Java model.
	 * This is a handle-only method.
	 *
	 * @return the Java model
	 */
	IVdmModel getJavaModel();

	/**
	 * Returns the Java project this element is contained in,
	 * or <code>null</code> if this element is not contained in any Java project
	 * (for instance, the <code>IJavaModel</code> is not contained in any Java
	 * project).
	 * This is a handle-only method.
	 *
	 * @return the containing Java project, or <code>null</code> if this element is
	 *   not contained in a Java project
	 */
	IVdmProject getVdmProject();

//	/**
//	 * Returns the first openable parent. If this element is openable, the element
//	 * itself is returned. Returns <code>null</code> if this element doesn't have
//	 * an openable parent.
//	 * This is a handle-only method.
//	 *
//	 * @return the first openable parent or <code>null</code> if this element doesn't have
//	 * an openable parent.
//	 * @since 2.0
//	 */
//	IOpenable getOpenable();

	/**
	 * Returns the element directly containing this element,
	 * or <code>null</code> if this element has no parent.
	 * This is a handle-only method.
	 *
	 * @return the parent element, or <code>null</code> if this element has no parent
	 */
	IVdmElement getParent();

	/**
	 * Returns the path to the innermost resource enclosing this element.
	 * If this element is not included in an external library,
	 * the path returned is the full, absolute path to the underlying resource,
	 * relative to the workbench.
	 * If this element is included in an external library,
	 * the path returned is the absolute path to the archive or to the
	 * folder in the file system.
	 * This is a handle-only method.
	 *
	 * @return the path to the innermost resource enclosing this element
	 * @since 2.0
	 */
	IPath getPath();

	/**
	 * Returns the primary element (whose compilation unit is the primary compilation unit)
	 * this working copy element was created from, or this element if it is a descendant of a
	 * primary compilation unit or if it is not a descendant of a working copy (e.g. it is a
	 * binary member).
	 * The returned element may or may not exist.
	 *
	 * @return the primary element this working copy element was created from, or this
	 * 			element.
	 * @since 3.0
	 */
	IVdmElement getPrimaryElement();

	/**
	 * Returns the innermost resource enclosing this element.
	 * If this element is included in an archive and this archive is not external,
	 * this is the underlying resource corresponding to the archive.
	 * If this element is included in an external library, <code>null</code>
	 * is returned.
	 * This is a handle-only method.
	 *
	 * @return the innermost resource enclosing this element, <code>null</code> if this
	 * element is included in an external archive
	 * @since 2.0
	 */
	IResource getResource();

	/**
	 * Returns the scheduling rule associated with this Java element.
	 * This is a handle-only method.
	 *
	 * @return the scheduling rule associated with this Java element
	 * @since 3.0
	 */
	ISchedulingRule getSchedulingRule();

	/**
	 * Returns the smallest underlying resource that contains
	 * this element, or <code>null</code> if this element is not contained
	 * in a resource.
	 *
	 * @return the underlying resource, or <code>null</code> if none
	 * @exception JavaModelException if this element does not exist or if an
	 *		exception occurs while accessing its underlying resource
	 */
	IResource getUnderlyingResource() throws VdmModelException;

	/**
	 * Returns whether this Java element is read-only. An element is read-only
	 * if its structure cannot be modified by the java model.
	 * <p>
	 * Note this is different from IResource.isReadOnly(). For example, .jar
	 * files are read-only as the java model doesn't know how to add/remove
	 * elements in this file, but the underlying IFile can be writable.
	 * <p>
	 * This is a handle-only method.
	 *
	 * @return <code>true</code> if this element is read-only
	 */
	boolean isReadOnly();

//	/**
//	 * Returns whether the structure of this element is known. For example, for a
//	 * compilation unit that has syntax errors, <code>false</code> is returned.
//	 * If the structure of an element is unknown, navigations will return reasonable
//	 * defaults. For example, <code>getChildren</code> for a compilation unit with
//	 * syntax errors will return a collection of the children that could be parsed.
//	 * <p>
//	 * Note: This does not imply anything about consistency with the
//	 * underlying resource/buffer contents.
//	 * </p>
//	 *
//	 * @return <code>true</code> if the structure of this element is known
//	 * @exception JavaModelException if this element does not exist or if an
//	 *		exception occurs while accessing its corresponding resource
//	 */// TODO (philippe) predicate shouldn't throw an exception
//	boolean isStructureKnown() throws JavaModelException;
}