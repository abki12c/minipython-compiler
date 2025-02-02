/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class ADivTerm extends PTerm
{
    private PTerm _term_;
    private TDiv _div_;
    private PFactor _factor_;

    public ADivTerm()
    {
    }

    public ADivTerm(
        PTerm _term_,
        TDiv _div_,
        PFactor _factor_)
    {
        setTerm(_term_);

        setDiv(_div_);

        setFactor(_factor_);

    }
    public Object clone()
    {
        return new ADivTerm(
            (PTerm) cloneNode(_term_),
            (TDiv) cloneNode(_div_),
            (PFactor) cloneNode(_factor_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADivTerm(this);
    }

    public PTerm getTerm()
    {
        return _term_;
    }

    public void setTerm(PTerm node)
    {
        if(_term_ != null)
        {
            _term_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _term_ = node;
    }

    public TDiv getDiv()
    {
        return _div_;
    }

    public void setDiv(TDiv node)
    {
        if(_div_ != null)
        {
            _div_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _div_ = node;
    }

    public PFactor getFactor()
    {
        return _factor_;
    }

    public void setFactor(PFactor node)
    {
        if(_factor_ != null)
        {
            _factor_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _factor_ = node;
    }

    public String toString()
    {
        return ""
            + toString(_term_)
            + toString(_div_)
            + toString(_factor_);
    }

    void removeChild(Node child)
    {
        if(_term_ == child)
        {
            _term_ = null;
            return;
        }

        if(_div_ == child)
        {
            _div_ = null;
            return;
        }

        if(_factor_ == child)
        {
            _factor_ = null;
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_term_ == oldChild)
        {
            setTerm((PTerm) newChild);
            return;
        }

        if(_div_ == oldChild)
        {
            setDiv((TDiv) newChild);
            return;
        }

        if(_factor_ == oldChild)
        {
            setFactor((PFactor) newChild);
            return;
        }

    }
}
