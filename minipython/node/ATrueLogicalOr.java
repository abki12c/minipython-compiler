/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class ATrueLogicalOr extends PLogicalOr
{

    public ATrueLogicalOr()
    {
    }
    public Object clone()
    {
        return new ATrueLogicalOr();
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseATrueLogicalOr(this);
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