package com.github.agadar.nationstates.enumerator;

import javax.xml.bind.annotation.adapters.XmlAdapter;

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
     * Return the underlying council number.
     *
     * @return the underlying council number
     */
    public int toInt() {
        return councilNumber;
    }

    /**
     * Returns the Council instance that has the given council number.
     *
     * @param councilNumber the council number
     * @return the corresponding Council
     */
    public static Council fromInt(int councilNumber) {
        switch (councilNumber) {
            case 1:
                return GENERAL_ASSEMBLY;
            case 2:
                return SECURITY_COUNCIL;
            default:
                throw new IllegalArgumentException("'councilNumber' should be either 1 or 2");
        }
    }

    /**
     * Instantiate a new entry with the given underlying council number.
     *
     * @param councilNumber the underlying council number
     */
    private Council(int councilNumber) {
        this.councilNumber = councilNumber;
    }

    /**
     * Converts an integer to a Council enum value and vice versa.
     */
    public static class Adapter extends XmlAdapter<Integer, Council> {

        @Override
        public Integer marshal(Council bt) {
            return bt.councilNumber;
        }

        @Override
        public Council unmarshal(Integer vt) {
            return Council.fromInt(vt);
        }
    }
}
