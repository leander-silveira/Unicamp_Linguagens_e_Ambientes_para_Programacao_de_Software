    // Regex to match ISBN (either ISBN-10 or ISBN-13)
//    private static final String ISBN_REGEX = "(^\\d{9}[\\dX]$)|(^\\\\d{13}$)";
    // Regex to match strings with letters, including accented characters, spaces, and ,.&'-
//    private static final String GENERAL_STRING_REGEX = "^[\\p{L} ,.&'-]+$";
    // Define the maximum length for various fields
//   private static final int PUBLISHER_MAX_LENGTH = 100;
//   private static final int DESC_MAX_LENGTH = 1000;
//    private static final int THUMBNAIL_MAX_LENGTH = 200;
//   private static final int IMAGE_MAX_LENGTH = 200;
//    private static final int DIMENSIONS_MAX_LENGTH = 50;

package esoft.book.model;

import java.util.Date;

import esoft.abs.model.Item;
import esoft.com.util.Validator;

public class Book extends Item {
    private Date pubDate;
    private String publisher;
    private Subject subject;
    private String desc;
    private String thumbnail;
    private String image;
    private double srp;
    private Date avail;
    private String isbn;
    private int page;
    private Backing backing;
    private String dimensions;
    private Author author;

    private static final String ISBN_REGEX = "(^\\d{9}[\\dX]$)|(^\\d{13}$)";
    private static final String GENERAL_STRING_REGEX = "^[\\p{L} ,.&'-]+$";
    private static final int PUBLISHER_MAX_LENGTH = 100;
    private static final int DESC_MAX_LENGTH = 1000;
    private static final int THUMBNAIL_MAX_LENGTH = 200;
    private static final int IMAGE_MAX_LENGTH = 200;
    private static final int DIMENSIONS_MAX_LENGTH = 50;

    public Book(int id, String title, Date pubDate, String publisher, Subject subject, String desc, 
                String thumbnail, String image, double srp, Date avail, String isbn, int page, 
                Backing backing, String dimensions, Author author) {
        super(id, title);
/*        Validator.validateNotNull(pubDate, publisher);
        Validator.validateStringMaxLength(publisher, "", PUBLISHER_MAX_LENGTH);
        Validator.validateStringMaxLength(desc, "", DESC_MAX_LENGTH);
        Validator.validateStringMaxLength(thumbnail, "", THUMBNAIL_MAX_LENGTH);
        Validator.validateStringMaxLength(image, "", IMAGE_MAX_LENGTH);
        Validator.validateStringMaxLength(dimensions, "", DIMENSIONS_MAX_LENGTH);
        Validator.validateString(isbn, "Invalid ISBN format", ISBN_REGEX);
        Validator.validatePositive(page, "asdf");*/
        
        Validator.validateNotNull(pubDate, "Data de publicação não pode ser nula.");
    	Validator.validateNotNull(avail, "Data de disponibilidade não pode ser nula.");
        Validator.validateNotNull(subject, "Assunto não pode ser nulo.");
        Validator.validateNotNull(backing, "Tipo de encadernação não pode ser nulo.");
        Validator.validateNotNull(author, "Autor não pode ser nulo.");
        
        Validator.validatePositive(srp, "");
        Validator.validatePositive(page, "");
        
        Validator.validateString(isbn, "ISBN", ISBN_REGEX);
        Validator.validateString(publisher, "", GENERAL_STRING_REGEX);
        Validator.validateString(desc, "", GENERAL_STRING_REGEX);
        Validator.validateString(thumbnail, "", GENERAL_STRING_REGEX);
        Validator.validateString(image, "", GENERAL_STRING_REGEX);
        Validator.validateString(dimensions, "", GENERAL_STRING_REGEX);
        
        Validator.validateStringMaxLength(publisher, "", PUBLISHER_MAX_LENGTH);
        Validator.validateStringMaxLength(desc, "", DESC_MAX_LENGTH);
        Validator.validateStringMaxLength(thumbnail, "", THUMBNAIL_MAX_LENGTH);
        Validator.validateStringMaxLength(image, "", IMAGE_MAX_LENGTH);
        Validator.validateStringMaxLength(dimensions, "", DIMENSIONS_MAX_LENGTH);


        this.pubDate = pubDate;
        this.publisher = publisher;
        this.subject = subject;
        this.desc = desc;
        this.thumbnail = thumbnail;
        this.image = image;
        this.srp = srp;
        this.avail = avail;
        this.isbn = isbn;
        this.page = page;
        this.backing = backing;
        this.dimensions = dimensions;
        this.author = author;
    }

    // Getters and setters
    public Date getPubDate() { return pubDate; }

    public String getPublisher() { return publisher; }

    public Subject getSubject() { return subject; }

    public String getDesc() { return desc; }

    public String getThumbnail() { return thumbnail; }

    public String getImage() { return image; }

    public double getSrp() { return srp; }

    public Date getAvail() { return avail; }

    public String getIsbn() { return isbn; }

    public int getPage() { return page; }

    public Backing getBacking() { return backing; }

    public String getDimensions() { return dimensions; }

    public Author getAuthor() { return author; }
}
