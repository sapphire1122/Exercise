import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.testCategorizedBooks();


    }

    public void testCategorizedBooks() {
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
        Map<Category, List<Book>> categorizedBooks = categorizeBooks(books);

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


    public Map<Category, List<Book>>  categorizeBooks(ArrayList<Book> books){
        Map<Category, List<Book>> categoryListMap= new HashMap<>();
        //Loop over each book in the given list.
        for(Book book : books){
            for(Category category: book.categories){
                // Get the list of books for this category. If the category doesn't exist yet,
                // use an empty list as the default.
                List<Book> booksInSameCategory = categoryListMap.getOrDefault(category, new ArrayList<>());
                booksInSameCategory.add(book);
                categoryListMap.put(category,booksInSameCategory);
            }
        }
        return categoryListMap;
    }


/* stream solution
//books.stream() creates a Stream of books from the list.
//The flatMap function transforms each book into a Stream of category-book pairs (AbstractMap.SimpleEntry).
//collect gathers the entries into a Map. It uses Collectors.groupingBy to group the pairs by category (Map.Entry::getKey), mapping each pair to its book (Map.Entry::getValue), and then collecting these into a List (Collectors.toList()).
    public Map<Category, List<Book>> categorizeBooks(List<Book> books) {
        return books.stream()
                .flatMap(book -> book.categories.stream()
                        .map(category -> new AbstractMap.SimpleEntry<>(category, book)))
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }
*/

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



