package service;

import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import dao.HibernateDAO;
import domain.Product;

@Path("product")
public class ProductService {

  @GET
  @Path("getAllProducts")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Product> getAllProducts() {
    return HibernateDAO.getInstance().returnProductList();
  }

  @PUT
  @Path("addProduct/name/{productname}/description/{productdescription}")
  public void addProduct(@PathParam("productname") String productname,
    @PathParam("productdescription") String productdescription) {
    Product product = new Product();
    product.setName(productname);
    product.setPrice(Integer.parseInt(productdescription));
    HibernateDAO.getInstance().insertObject(product);;
  }

  @DELETE
  @Path("deleteProduct/{productid}")
  public void deleteProduct(@PathParam("productid") String productid) {
    Long productId = Long.parseLong(productid);
    HibernateDAO.getInstance().deleteObject(HibernateDAO.getInstance().returnProduct(productId));
  }

  @POST
  @Path("updateProduct/id/{productid}/name/{productname}/description/{productdescription}")
  public void updateProduct(@PathParam("productid") String productid,
    @PathParam("productname") String productname,
    @PathParam("productdescription") String productdescription) {
    Long productId = Long.parseLong(productid);
    Product product = HibernateDAO.getInstance().returnProduct(productId);
    product.setName(productname);
    product.setPrice(Integer.parseInt(productdescription));
    HibernateDAO.getInstance().updateObject(product);
  }
}

