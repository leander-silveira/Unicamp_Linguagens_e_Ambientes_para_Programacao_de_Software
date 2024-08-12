package esoft.book.model;

/*
 * Author.java - Data about an author. 
 * 
 ************************************************************************
 *
 *
 ************************************************************************/
import esoft.com.util.Validator;
import java.util.Date;

/**
 * <pre>
 * This is a Java class called "Author" that represents data about an author. It
 * contains five private fields: "fname", "mname", "lname", "birthdate", and
 * "bio", which respectively represent the author's first name, middle name,
 * last name, birth date, and biography. These fields are initialized through a
 * constructor that takes five parameters: fname, mname, lname, birthdate, and
 * bio.
 *
 * The class provides five public getter methods: "getFname()", "getLname()",
 * "getMname()", "getBirthdate()", and "getBio()", which allow external code to
 * access the values of the private fields.
 *
 * This class implements the Serializable interface, which means that objects of
 * this class can be serialized and deserialized.
 * </pre>
 * <img src="./doc-files/Author.png" alt="Author">
 */
public class Author {

    private static final long serialVersionUID = 8882043540800200706L;

    private final String fname;
    private final String mname;
    private final String lname;
    private final Date birthdate;
    private final String bio;

    // Regex to match names with letters, including accented characters
    private static final String NAME_REGEX = "^[\\p{L} .'-]+$";
    private static final String BIO_REGEX = "^[\\s\\S]{3,}$"; // Regex para validar bio (mínimo 3 caracteres)

    // Define the maximum length for name and bio fields
    private static final int NAME_MAX_LENGTH = 50;
    private static final int BIO_MAX_LENGTH = 500;

    /**
     *
     * @param fname
     * @param mname
     * @param lname
     * @param birthdate
     * @param bio
     */
    public Author(final String fname, final String mname, final String lname,
            final Date birthdate, final String bio) {
        Validator.validateString(fname, "First name", NAME_REGEX);
        Validator.validateStringMaxLength(fname, "First name", NAME_MAX_LENGTH);
        Validator.validateStringNotEmpty(mname, "Middle name");
        Validator.validateStringMaxLength(mname, "First name", NAME_MAX_LENGTH);
        Validator.validateStringNotEmpty(lname, "Last name");
        Validator.validateStringMaxLength(lname, "First name", NAME_MAX_LENGTH);
        Validator.validateNotNull(birthdate, "Birthdate");
        Validator.validateString(bio, "Bio", BIO_REGEX);
        Validator.validateStringMaxLength(bio, "Bio", BIO_MAX_LENGTH);

        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.birthdate = birthdate;
        this.bio = bio;
    }

    /**
     *
     * @return
     */
    public String getFname() {
        return fname;
    }

    /**
     *
     * @return
     */
    public String getLname() {
        return lname;
    }

    /**
     *
     * @return
     */
    public String getMname() {
        return mname;
    }

    /**
     *
     * @return
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     *
     * @return
     */
    public String getBio() {
        return bio;
    }

    @Override
    public String toString() {
        return "Author{" + "fname=" + fname + ", mname=" + mname + ", lname=" + lname + ", birthdate=" + birthdate + ", bio=" + bio + '}';
    }

}
