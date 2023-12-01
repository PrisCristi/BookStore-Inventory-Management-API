package org.example;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/books")
    public class BookResource {
        BookDao bookDao = new BookDao();
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<Book> getBooks() {
            return bookDao.getAllBooks();
        }
        @GET
        @Path("/{title}")
        @Produces(MediaType.APPLICATION_JSON)
        public Book getbook() {
            return bookDao.getBook(getbook().getTitle());
        }
        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        public void addBook(Book book) {
            bookDao.addbook(book);
        }
        @PUT
        @Path("/{id}")
        @Consumes(MediaType.APPLICATION_JSON)
        public void updateProduct(@PathParam("id") int id, Book book) {
            bookDao.updateBook(id, book);
        }
        @DELETE
        @Path("/{id}")
        public void deleteBook(@PathParam("id") int id) {
            bookDao.deleteBook(id);
        }
    }
}
