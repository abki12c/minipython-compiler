/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class ANotEqLogicalOr extends PLogicalOr
{
    private PExpression _expL_;
    private PExpression _expR_;

    public ANotEqLogicalOr()
    {
    }

    public ANotEqLogicalOr(
        PExpression _expL_,
        PExpression _expR_)
    {
        setExpL(_expL_);

        setExpR(_expR_);

    }
    public Object clone()
    {
        return new ANotEqLogicalOr(
            (PExpression) cloneNode(_expL_),
            (PExpression) cloneNode(_expR_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANotEqLogicalOr(this);
    }

    public PExpression getExpL()
    {
        return _expL_;
    }

    public void setExpL(PExpression node)
    {
        if(_expL_ != null)
        {
            _expL_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _expL_ = node;
    }

    public PExpression getExpR()
    {
        return _expR_;
    }

    public void setExpR(PExpression node)
    {
        if(_expR_ != null)
        {
            _expR_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _expR_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_expL_)
            + toString(_expR_);
    }

    void removeChild(Node child)
    {
        if(_expL_ == child)
        {
            _expL_ = null;
            return;
        }

        if(_expR_ == child)
        {
            _expR_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_expL_ == oldChild)
        {
            setExpL((PExpression) newChild);
            return;
        }

        if(_expR_ == oldChild)
        {
            setExpR((PExpression) newChild);
            return;
        }

    }
}
