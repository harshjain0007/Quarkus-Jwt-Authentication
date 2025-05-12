package acme;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.security.Authenticated;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class BookResource {

    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Book>> listAll() {
        return Book.listAll();
    }

    @GET
    @RolesAllowed({"admin","writer"})
    @Path("/{id}")
    public Uni<Response> getBook(@PathParam("id") Long id) {
        return Book.findById(id)
                .onItem().ifNotNull().transform(book -> Response.ok(book).build())
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND)::build);
    }
    @POST
    @RolesAllowed({"admin","writer"})
    public Uni<Response> addBook(Book book) {
        book.id = null; // Ensure the book is treated as new
        return Panache.withTransaction(() ->
                book.<Book>persist()
                        .onItem().transform(inserted ->
                                Response.status(Response.Status.CREATED).entity(inserted).build()
                        )
        );
    }

    @PUT
    @RolesAllowed({"admin","writer"})
    @Path("/{id}")
    public Uni<Response> updateBook(@PathParam("id") Long id, Book updatedBook) {
        return Book.findById(id)
                .onItem().ifNotNull().transformToUni(entity -> {
                    Book book = (Book) entity;
                    book.title = updatedBook.title;
                    book.author = updatedBook.author;
                    book.yearPublished = updatedBook.yearPublished;
                    return book.persist().replaceWith(Response.ok(book).build());
                })
                .onItem().ifNull().continueWith(Response.status(Response.Status.NOT_FOUND)::build);
    }

    @DELETE
    @RolesAllowed("admin")
    @Path("/{id}")
    public Uni<Response> deleteBook(@PathParam("id") Long id) {
        return Panache.withTransaction(() ->
                Book.deleteById(id)
                        .map(deleted -> deleted
                                ? Response.noContent().build()
                                : Response.status(Response.Status.NOT_FOUND).build()
                        )
        );
    }
}
