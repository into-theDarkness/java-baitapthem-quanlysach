package tranminhduc.pro.controller;

import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import tranminhduc.pro.model.Book;
import tranminhduc.pro.service.BookService;

@Controller
public class BookController {
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
    public ModelAndView listBook(Pageable pageable){
        Page<Book> books = bookService.findAll(pageable);
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
}
