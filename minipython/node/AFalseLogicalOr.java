/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class AFalseLogicalOr extends PLogicalOr
{

    public AFalseLogicalOr()
    {
    }
    public Object clone()
    {
        return new AFalseLogicalOr();
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFalseLogicalOr(this);
    }

    public String toString()
    {
        return "";
    }

    void removeChild(Node child)
    {
    }

    void replaceChild(Node oldChild, Node newChild)
    {
    }
}
