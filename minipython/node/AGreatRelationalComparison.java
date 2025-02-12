/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class AGreatRelationalComparison extends PRelationalComparison
{
    private PExpression _expL_;
    private TGreat _great_;
    private PExpression _expR_;

    public AGreatRelationalComparison()
    {
    }

    public AGreatRelationalComparison(
        PExpression _expL_,
        TGreat _great_,
        PExpression _expR_)
    {
        setExpL(_expL_);

        setGreat(_great_);

        setExpR(_expR_);

    }
    public Object clone()
    {
        return new AGreatRelationalComparison(
            (PExpression) cloneNode(_expL_),
            (TGreat) cloneNode(_great_),
            (PExpression) cloneNode(_expR_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAGreatRelationalComparison(this);
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

    public TGreat getGreat()
    {
        return _great_;
    }

    public void setGreat(TGreat node)
    {
        if(_great_ != null)
        {
            _great_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _great_ = node;
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
            + toString(_great_)
            + toString(_expR_);
    }

    void removeChild(Node child)
    {
        if(_expL_ == child)
        {
            _expL_ = null;
            return;
        }

        if(_great_ == child)
        {
            _great_ = null;
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

        if(_great_ == oldChild)
        {
            setGreat((TGreat) newChild);
            return;
        }

        if(_expR_ == oldChild)
        {
            setExpR((PExpression) newChild);
            return;
        }

    }
}
