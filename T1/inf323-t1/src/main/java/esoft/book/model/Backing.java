package esoft.book.model;

/**
 * <pre>
 * This is a Java enum named "BACKINGS" that defines various book backings as
 * its elements.
 *
 * The enum defines 5 elements, each representing a type of book binding. These
 * backings include "Hardback", "Paperback", "Used", "Audio", and "Limited
 * Edition".
 *
 * Enums are a type of class in Java that represent a fixed set of constants. In
 * this case, the constants represent the different book binding types. Enums
 * are often used to define types of data that have a known, finite set of
 * values, such as days of the week, colors, or categories.
 * </pre>
 * <img src="./doc-files/Backing.png" alt="Backing">
 *
 * @author esoft
 */
public enum Backing {
    HARDBACK, PAPERBACK, USED, AUDIO,
    LIMITED_EDITION;
}
