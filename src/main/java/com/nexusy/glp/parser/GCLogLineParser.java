package com.nexusy.glp.parser;

/**
 * @author lanhuidong
 * @since 2016-12-07
 */
public interface GCLogLineParser<T> {

    T parse(String line);
}
