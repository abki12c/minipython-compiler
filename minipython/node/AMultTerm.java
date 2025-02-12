/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class AMultTerm extends PTerm
{
    private PTerm _term_;
    private TMult _mult_;
    private PFactor _factor_;

    public AMultTerm()
    {
    }

    public AMultTerm(
        PTerm _term_,
        TMult _mult_,
        PFactor _factor_)
    {
        setTerm(_term_);

        setMult(_mult_);

        setFactor(_factor_);

    }
    public Object clone()
    {
        return new AMultTerm(
            (PTerm) cloneNode(_term_),
            (TMult) cloneNode(_mult_),
            (PFactor) cloneNode(_factor_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMultTerm(this);
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

    public TMult getMult()
    {
        return _mult_;
    }

    public void setMult(TMult node)
    {
        if(_mult_ != null)
        {
            _mult_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _mult_ = node;
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
            + toString(_mult_)
            + toString(_factor_);
    }

    void removeChild(Node child)
    {
        if(_term_ == child)
        {
            _term_ = null;
            return;
        }

        if(_mult_ == child)
        {
            _mult_ = null;
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

        if(_mult_ == oldChild)
        {
            setMult((TMult) newChild);
            return;
        }

        if(_factor_ == oldChild)
        {
            setFactor((PFactor) newChild);
            return;
        }

    }
}
