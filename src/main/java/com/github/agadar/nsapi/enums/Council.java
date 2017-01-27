package com.github.agadar.nsapi.enums;

/**
 * The different councils within the World Assembly.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum Council {

    /**
     * The General Assembly
     */
    GENERAL_ASSEMBLY(1),
    /**
     * The Security Council
     */
    SECURITY_COUNCIL(2);

    /**
     * The underlying council number
     */
    private final int councilNumber;

    /**
     * Instantiate a new entry with the given underlying council number.
     *
     * @param councilNumber the underlying council number
     */
    private Council(int councilNumber) {
        this.councilNumber = councilNumber;
    }

    /**
     * Return the underlying council number.
     *
     * @return the underlying council number
     */
    public int getCouncilNumber() {
        return councilNumber;
    }

    /**
     * Returns the Council instance that has the given council number.
     *
     * @param councilNumber the council number
     * @return the corresponding Council
     */
    public static Council getByNumber(int councilNumber) {
        return values()[councilNumber - 1];
    }
}
