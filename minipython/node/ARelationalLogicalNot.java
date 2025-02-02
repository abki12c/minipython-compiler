/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class ARelationalLogicalNot extends PLogicalNot
{
    private PRelationalComparison _relationalComparison_;

    public ARelationalLogicalNot()
    {
    }

    public ARelationalLogicalNot(
        PRelationalComparison _relationalComparison_)
    {
        setRelationalComparison(_relationalComparison_);

    }
    public Object clone()
    {
        return new ARelationalLogicalNot(
            (PRelationalComparison) cloneNode(_relationalComparison_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseARelationalLogicalNot(this);
    }

    public PRelationalComparison getRelationalComparison()
    {
        return _relationalComparison_;
    }

    public void setRelationalComparison(PRelationalComparison node)
    {
        if(_relationalComparison_ != null)
        {
            _relationalComparison_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _relationalComparison_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_relationalComparison_);
    }

    void removeChild(Node child)
    {
        if(_relationalComparison_ == child)
        {
            _relationalComparison_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_relationalComparison_ == oldChild)
        {
            setRelationalComparison((PRelationalComparison) newChild);
            return;
        }

    }
}
