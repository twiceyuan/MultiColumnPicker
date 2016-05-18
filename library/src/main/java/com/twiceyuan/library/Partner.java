package com.twiceyuan.library;


import com.twiceyuan.library.func.Call;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by twiceYuan on 4/14/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 * <p>
 * 如果在两个可能并发的代码中执行两段代码 A 和 B, 其中 B 需要等 A 执行过才可以执行
 * <p>
 * 等待期间不会阻塞当前线程
 */
public final class Partner {

    private List<Call> mAfterAction  = new LinkedList<>();
    private Call       mBeforeAction = null;

    private final Object monitor = new Object();

    private Partner() {
    }

    public static Partner build() {
        return new Partner();
    }

    /**
     * 构造一个和另一个 Partner(P-A) 配合的 Partner(P-B)
     * <p>
     * 只有当 P-A 或者 P-B 两者其中任意执行 before 方法后, P-B 的 after 才会被触发
     *
     * @param anotherPartner 另一个 Partner, 可以触发本 partner 的 after
     */
    public static Partner build(Partner anotherPartner) {
        final Partner partner = new Partner();
        anotherPartner.after(new Call() {
            @Override
            public void call() {
                partner.before();
            }
        });
        return partner;
    }

    /**
     * 在 after 中的 action 执行之前需要执行过 before 的 action 或者 before 方法本身
     */
    public void before(Call call) {
        synchronized (monitor) {
            mBeforeAction = call == null ? nullCall : call;
            if (call != null) {
                call.call();
            }
            //noinspection Convert2streamapi
            for (Call c : new LinkedList<>(mAfterAction)) {
                if (c != null) {
                    c.call();
                    mAfterAction.remove(c);
                }
            }
        }
    }

    public void before() {
        before(null);
    }

    /**
     * after 中的 action 会在 before 方法执行过才会执行
     *
     * @param after before 方法执行过需要执行的代码
     */
    public void after(Call after) {
        synchronized (monitor) {
            if (mBeforeAction == null) {
                mAfterAction.add(after);
            } else {
                if (after != null) {
                    after.call();
                }
            }
        }
    }

    @SuppressWarnings("Convert2Lambda")
    private static Call nullCall = new Call() {
        @Override
        public void call() {
        }
    };
}
