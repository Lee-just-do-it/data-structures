package com.java.javasources.structures.hash;

/**
 * @author
 * @desc
 * @date
 * @since
 */
@SuppressWarnings("all")
public interface HashFamily<AnyType> {
    int hash(AnyType x, int which);

    int getNumberOfFunctions();

    void generateNewFunctions();
}
