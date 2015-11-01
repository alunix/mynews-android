package com.qchu.once.shared.network;

/**
 * Created by quocdungchu on 31/10/15.
 */
public interface Parser<Parsed> {
	Parsed parse(String fromString);
}
