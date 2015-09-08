package com.twiceyuan.library;

/**
 * Created by twiceYuan on 9/8/15.
 *
 * 根据实体映射其 ID 的接口
 */
public interface MapId<T> {
    Object getId(T t);
}
