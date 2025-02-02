/* This file was generated by SableCC (http://www.sablecc.org/). */

package minipython.node;

import java.util.*;
import minipython.analysis.*;

public final class APrintStatement extends PStatement
{
    private PExpression _expression_;
    private final LinkedList _commaExpression_ = new TypedLinkedList(new CommaExpression_Cast());

    public APrintStatement()
    {
    }

    public APrintStatement(
        PExpression _expression_,
        List _commaExpression_)
    {
        setExpression(_expression_);

        {
            this._commaExpression_.clear();
            this._commaExpression_.addAll(_commaExpression_);
        }

    }
    public Object clone()
    {
        return new APrintStatement(
            (PExpression) cloneNode(_expression_),
            cloneList(_commaExpression_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAPrintStatement(this);
    }

    public PExpression getExpression()
    {
        return _expression_;
    }

    public void setExpression(PExpression node)
    {
        if(_expression_ != null)
        {
            _expression_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        _expression_ = node;
    }

    public LinkedList getCommaExpression()
    {
        return _commaExpression_;
    }

    public void setCommaExpression(List list)
    {
        _commaExpression_.clear();
        _commaExpression_.addAll(list);
    }

    public String toString()
    {
        return ""
            + toString(_expression_)
            + toString(_commaExpression_);
    }

    void removeChild(Node child)
    {
        if(_expression_ == child)
        {
            _expression_ = null;
            return;
        }

        if(_commaExpression_.remove(child))
        {
            return;
        }

    }

    void replaceChild(Node oldChild, Node newChild)
    {
        if(_expression_ == oldChild)
        {
            setExpression((PExpression) newChild);
            return;
        }

        for(ListIterator i = _commaExpression_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set(newChild);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

    }

    private class CommaExpression_Cast implements Cast
    {
        public Object cast(Object o)
        {
            PCommaExpression node = (PCommaExpression) o;

            if((node.parent() != null) &&
                (node.parent() != APrintStatement.this))
            {
                node.parent().removeChild(node);
            }

            if((node.parent() == null) ||
                (node.parent() != APrintStatement.this))
            {
                node.parent(APrintStatement.this);
            }

            return node;
        }
    }
}
