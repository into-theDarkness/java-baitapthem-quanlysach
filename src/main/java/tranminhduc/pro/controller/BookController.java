package tranminhduc.pro.controller;

import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tranminhduc.pro.model.Book;
import tranminhduc.pro.model.Category;
import tranminhduc.pro.service.BookService;
import tranminhduc.pro.service.CategoryService;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
public class BookController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BookService bookService;
    @GetMapping("/create-book")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView =new ModelAndView("book/create");
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }
    @PostMapping("/create-book")
    public ModelAndView saveBook(Book book){
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("book/create");
        modelAndView.addObject("book", new Book());
        modelAndView.addObject("message","book has been created");
        return modelAndView;
    }
    @GetMapping("/books")
    public ModelAndView listBook(@RequestParam("s") Optional<String> s, @PageableDefault(size = 5) Pageable pageable){
        Page<Book> books;
        if(s.isPresent()){
            books =bookService.findAllByNameContaining(s.get(), pageable);
        } else {
            books = bookService.findAll(pageable);
        }
            ModelAndView modelAndView = new ModelAndView("book/list");
            modelAndView.addObject("books", books);
            return modelAndView;
    }
    @GetMapping("/edit-book/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id){
        Book book = bookService.findById(id);
        if(book!=null){
            ModelAndView modelAndView = new ModelAndView("book/edit");
            modelAndView.addObject("book",book);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error");
            return modelAndView;
        }
    }
    @PostMapping("/edit-book")
    public ModelAndView updateBook(Book book){
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("book/edit");
        modelAndView.addObject("book", book);
        modelAndView.addObject("message","book has been updated");
        return modelAndView;

    }
    @GetMapping("/delete-book/{id}")
    public ModelAndView deleteForm(@PathVariable("id") Long id){
        Book book = bookService.findById(id);
        if(book!=null){
            ModelAndView modelAndView = new ModelAndView("book/delete");
            modelAndView.addObject("book", book);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error");
            return modelAndView;
        }
    }
    @PostMapping("/delete-book")
    public String deleteBook(@ModelAttribute("book") Book book){
        bookService.remove(book.getId());
        return "redirect:books";
    }
    @ModelAttribute("category")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }
    @GetMapping("/book-manager")
    public ModelAndView bookManager(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
    @GetMapping("/view-category/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id){
        Category category = categoryService.findById(id);
        if(category == null){
            return new ModelAndView("/error");
        }

        Iterable<Book> books = bookService.findAllByCategory(category);

        ModelAndView modelAndView = new ModelAndView("/category/view");
        modelAndView.addObject("category", category);
        modelAndView.addObject("books", books);
        return modelAndView;
    }
}
