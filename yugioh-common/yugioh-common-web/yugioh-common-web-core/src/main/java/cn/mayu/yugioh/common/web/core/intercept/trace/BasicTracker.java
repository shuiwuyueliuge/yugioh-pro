package cn.mayu.yugioh.common.web.core.intercept.trace;

import java.util.Map;

public class BasicTracker extends Tracker {

    private static Tracker tracker = new BasicTracker();

    private BasicTracker() {
    }

    public static Tracker getInstance() {
        return tracker;
    }

    @Override
    protected void genTraceParam(Map<String, String> map) {
    }
}
