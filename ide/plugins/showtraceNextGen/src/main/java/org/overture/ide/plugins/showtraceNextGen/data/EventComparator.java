package org.overture.ide.plugins.showtraceNextGen.data;
import java.util.Comparator;

import org.overture.interpreter.messages.rtlog.nextgen.INextGenEvent;

public class EventComparator implements Comparator<INextGenEvent>
{
    public int compare(INextGenEvent event1, INextGenEvent event2)
    {
        return (int)(event1.getTime() - event2.getTime());
    }
}