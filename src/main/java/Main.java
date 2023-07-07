import java.util.*;


public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.testCategorizedBooks();


    }
    public  void testCategorizedBooks() {
        // Create some categories
        Category cat1 = new Category();
        cat1.id = 1L;
        cat1.name = "category1";
        cat1.level = 1;

        Category cat2 = new Category();
        cat2.id = 2L;
        cat2.name = "Fantasy";
        cat2.level = 2;
        cat2.parent = cat1;

        Category cat3 = new Category();
        cat3.id = 3L;
        cat3.name = "category2";
        cat3.level = 1;

        Category cat4 = new Category();
        cat4.id = 4L;
        cat4.name = "Report";
        cat4.level = 2;
        cat4.parent = cat2;



        // Create some books
        Book book1 = new Book();
        book1.id = 1L;
        book1.title = "Book1";
        book1.categories = Arrays.asList(cat1, cat2);

        Book book2 = new Book();
        book2.id = 2L;
        book2.title = "Book2";
        book2.categories = Arrays.asList(cat3, cat4);

        Book book3 = new Book();
        book3.id = 3L;
        book3.title = "Book3";
        book3.categories = Arrays.asList(cat3, cat4);

        // Create book list
        ArrayList<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        Map<Category, List<Book>> categorizedBooks = categoriedBooks(books);

        // Check if the categories were assigned properly
        for (Map.Entry<Category, List<Book>> entry : categorizedBooks.entrySet()) {
            Category category = entry.getKey();
            List<Book> booksInCategory = entry.getValue();
            System.out.println("Category ID: " + category.id + ", Name: " + category.name);
            for (Book book : booksInCategory) {
                System.out.println("  Book ID: " + book.id + ", Title: " + book.title);
            }
        }
    }

    public Map<Category, List<Book>> categoriedBooks(ArrayList<Book> books){
        //create a Map to classify books by categories
        Map<Category, List<Book>> categorizedBooks = new HashMap<>();

        for(Book book: books) {
            for(Category category: book.categories){
                List<Book> booksInSameCategory = categorizedBooks.getOrDefault(category, new ArrayList<>());
                booksInSameCategory.add(book);
                categorizedBooks.put(category, booksInSameCategory);
            }
        }
        return categorizedBooks;
    }
    class Category {
        public Long id;
        public String name;
        public Integer level;
        public Category parent;

    }

    class Book {
        public Long id;
        public String title;
        //categories[0] = leaf Category;
        //categories[last] = root Category;
        public List<Category> categories;
        public String conditions;
    }

}



