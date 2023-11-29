package vn.edu.iuh.fit.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.convert.ProductPricePath;
import vn.edu.iuh.fit.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao {
    private final EntityManager manager;
    @Autowired
    public ProductDao(EntityManager manager) {
        this.manager = manager;
    }
    @Transactional
    public boolean add(Product p)
    {
        try {
            manager.persist(p);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Transactional
    public boolean update(Product p )
    {
        try {
            manager.merge(p);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Transactional
    public Product get(long id)
    {
        try {
            return manager.find(Product.class,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Transactional
    public List<Product> getAllProductList()
    {
        try {
            return manager.createQuery("select p from Product p", Product.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Transactional
    public boolean detele(long id)
    {
        try {
            Product p=manager.find(Product.class,id);
            p.setStatus(0);
            manager.merge(p);;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Transactional
    public boolean activeProduct(long id)
    {
        try {
            Product p=manager.find(Product.class,id);
            p.setStatus(1);
            manager.merge(p);;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Transactional
    public ProductPricePath getProductPricePathsOfProduct(long productID){
        ProductPricePath productPricePath=new ProductPricePath();
        try {
            String nativeQuery= "SELECT\n" +
                    "    p.ProductID,\n" +
                    "    p.Name,\n" +
                    "    pp.Price,\n" +
                    "    pi.Path\n" +
                    "FROM\n" +
                    "    product p\n" +
                    "INNER JOIN\n" +
                    "    productprice pp ON p.ProductID = pp.ProductID\n" +
                    "INNER JOIN\n" +
                    "    productimage pi ON p.ProductID = pi.ProductID\n" +
                    "WHERE\n" +
                    "    pp.PriceDate = (\n" +
                    "        SELECT\n" +
                    "            MAX(PriceDate)\n" +
                    "        FROM\n" +
                    "            productprice\n" +
                    "        WHERE\n" +
                    "            ProductID = p.ProductID\n" +
                    "    )\n" +
                    "    AND p.ProductID = ?;\n" +
                    "\n" +
                    "\n";
            Query query= manager.createNativeQuery(nativeQuery, Object[].class);
            query.setParameter(1, productID);
            Object[] objects= (Object[]) query.getSingleResult();
            productPricePath=new ProductPricePath((Long) objects[0], (String) objects[1], (Double) objects[2], (String) objects[3]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productPricePath;

    }
    @Transactional
    public List<ProductPricePath> getProductPricePaths(){
        List<ProductPricePath> list= new ArrayList<>();
        try {
            String nativeQuery= "SELECT\n" +
                    "    p.ProductID,\n" +
                    "    p.Name,\n" +
                    "    pp.Price,\n" +
                    "    pi.Path\n" +
                    "FROM\n" +
                    "    product p\n" +
                    "INNER JOIN\n" +
                    "    productprice pp ON p.ProductID = pp.ProductID\n" +
                    "INNER JOIN\n" +
                    "    productimage pi ON p.ProductID = pi.ProductID\n" +
                    "WHERE\n" +
                    "    pp.PriceDate = (\n" +
                    "        SELECT\n" +
                    "            MAX(PriceDate)\n" +
                    "        FROM\n" +
                    "            productprice\n" +
                    "        WHERE\n" +
                    "            ProductID = p.ProductID\n" +
                    "    )\n" +
                    "    AND p.Status = 1;";
            List<Object[]> objects= manager.createNativeQuery(nativeQuery, Object[].class).getResultList();
            for (Object[] object:objects) {
                list.add(new ProductPricePath((long)object[0],(String) object[1], (double) object[2], (String) object[3]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
    @Transactional
    public Page<ProductPricePath> getAll(int page, int size) {
        List<ProductPricePath> products= new ArrayList<>();
        try {
            String nativeQuery= "SELECT\n" +
                    "    p.ProductID,\n" +
                    "    p.Name,\n" +
                    "    pp.Price,\n" +
                    "    pi.Path\n" +
                    "FROM\n" +
                    "    product p\n" +
                    "INNER JOIN\n" +
                    "    productprice pp ON p.ProductID = pp.ProductID\n" +
                    "INNER JOIN\n" +
                    "    productimage pi ON p.ProductID = pi.ProductID\n" +
                    "WHERE\n" +
                    "    pp.PriceDate = (\n" +
                    "        SELECT\n" +
                    "            MAX(PriceDate)\n" +
                    "        FROM\n" +
                    "            productprice\n" +
                    "        WHERE\n" +
                    "            ProductID = p.ProductID\n" +
                    "    )\n" +
                    "    AND p.Status = 1;";
            List<Object[]> objects= manager.createNativeQuery(nativeQuery, Object[].class)
                    .setFirstResult(page * size)
                    .setMaxResults(size)
                    .getResultList();;
            for (Object[] object:objects) {
                products.add(new ProductPricePath((long)object[0],(String) object[1], (double) object[2], (String) object[3]));
            }
            long totalProducts = (long) manager.createQuery("select count(p) from Product p").getSingleResult();

            return new PageImpl<>(products, PageRequest.of(page, size), totalProducts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
