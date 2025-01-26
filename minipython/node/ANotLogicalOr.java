/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class ANotLogicalOr extends PLogicalOr
{
    private PLogicalOr _logicalOr_;

    public ANotLogicalOr()
    {
    }

    public ANotLogicalOr(
        PLogicalOr _logicalOr_)
    {
        setLogicalOr(_logicalOr_);

    }
    public Object clone()
    {
        return new ANotLogicalOr(
            (PLogicalOr) cloneNode(_logicalOr_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANotLogicalOr(this);
    }

    public PLogicalOr getLogicalOr()
    {
        return _logicalOr_;
    }

    public void setLogicalOr(PLogicalOr node)
    {
        if(_logicalOr_ != null)
        {
            _logicalOr_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _logicalOr_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_logicalOr_);
    }

    void removeChild(Node child)
    {
        if(_logicalOr_ == child)
        {
            _logicalOr_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_logicalOr_ == oldChild)
        {
            setLogicalOr((PLogicalOr) newChild);
            return;
        }

    }
}
