package entity;

import java.io.Serializable;

public abstract class AbstractEntity<ID extends Serializable> implements Serializable
{
    public abstract ID getId();
}
