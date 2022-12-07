package cc.xfl12345.mybigdata.server.common.api;

public interface OpenCloneable extends Cloneable {
    Object clone() throws CloneNotSupportedException;
}
