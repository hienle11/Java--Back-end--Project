package entity;

import java.io.Serializable;

public abstract class AbstractEntity<ID extends Serializable> implements Serializable
{
    public abstract ID getId();

    public abstract void setId(ID id);
}
