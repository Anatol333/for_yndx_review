package com.kozhukhar.task5.search;

import com.kozhukhar.carshop_online.exception.AppException;

import java.io.File;

/**
 * Interface for chain.
 * Chain will realizing search method and some field which will be
 * contains information about next element of chain.
 */
public interface FileSearchChain {

    /**
     * Some conditions for searching files
     * @param file input file
     * @throws AppException application exception
     */
    boolean search(File file) throws AppException;
}
