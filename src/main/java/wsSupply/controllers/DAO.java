package wsSupply.controllers;

import org.hibernate.*;
import wsSupply.model.*;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAO {
    private static SessionFactory factory;

    public DAO(){
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public Model addModel(String name, String type){
        Session session = factory.openSession();
        Transaction tx = null;
        Model model = null;
        try {
            tx = session.beginTransaction();
            model = new Model(name, type);
            session.save(model);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return model;
    }
    public void addNormList(List<Norm> norms){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for(Norm norm : norms) {
                session.save(norm);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public List<Requirement> getRequirements(){
        Session session = factory.openSession();
        Transaction tx = null;
        List<Requirement> requirements = new ArrayList<Requirement>();

        try {
            tx = session.beginTransaction();
            List<Object[]> results = session.createQuery(
                    "FROM Requirement AS requirement INNER JOIN requirement.rawMaterial").list();
            for(Object[] pair : results){
                Requirement requirement = (Requirement)pair[0];
                requirement.setRawMaterial((RawMaterial) pair[1]);
                requirements.add(requirement);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return requirements;
    }
    public List<Norm> getNorms(){
        Session session = factory.openSession();
        Transaction tx = null;
        List<Norm> norms = new ArrayList<Norm>();

        try {
            tx = session.beginTransaction();
            List<Object[]> results = session.createQuery(
                    "FROM Norm AS norm INNER JOIN norm.model INNER JOIN norm.rawMaterial").list();
            for(Object[] pair : results){
                Norm norm = (Norm)pair[0];
                norm.setModel((Model) pair[1]);
                norm.setRawMaterial((RawMaterial) pair[2]);
                norms.add(norm);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return norms;
    }
    public List<Model> getModels(){
        Session session = factory.openSession();
        Transaction tx = null;
        List<Model> models = new ArrayList<Model>();

        try {
            tx = session.beginTransaction();
            List<Object> results = session.createQuery(
                    "FROM Model").list();
            for(Object result : results){
                models.add((Model) result);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return models;
    }
    public List<RawMaterial> getRawMaterials(){
        Session session = factory.openSession();
        Transaction tx = null;
        List<RawMaterial> materials = new ArrayList<RawMaterial>();

        try {
            tx = session.beginTransaction();
            List<Object> results = session.createQuery(
                    "FROM RawMaterial").list();
            for(Object result : results){
                materials.add((RawMaterial) result);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return materials;
    }
    public Integer addRawMaterial(String name, String measureUnit){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer materialID = null;

        try {
            tx = session.beginTransaction();
            RawMaterial rawMaterial = new RawMaterial(name, measureUnit);
            materialID = (Integer) session.save(rawMaterial);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return materialID;
    }
    public void deleteRawMaterial(Integer materialID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            RawMaterial material = (RawMaterial)session.get(RawMaterial.class, materialID);
            session.delete(material);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void addRequirementsList(List<Requirement> requirements){
        Session session = factory.openSession();
        Transaction tx = null;
            try {
                tx = session.beginTransaction();
                for(Requirement requirement : requirements) {
                    session.save(requirement);
                }
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
    }
    public void clearRequirements(){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = String.format("delete from Requirement");
            Query query = session.createQuery(hql);
            query.executeUpdate();
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void updateRequirement(Requirement requirement){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(requirement);
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    /*
    public User getUser(int id){
        Session session = factory.openSession();
        Transaction tx = null;
        User user = null;

        try {
            tx = session.beginTransaction();
            user = (User)session.get(User.class, id);
            List<Object[]> allIdeas = session.createQuery(
                    "FROM Idea AS idea INNER JOIN idea.user").list();
            List<Idea> userIdeas = new ArrayList<Idea>();
            for(Object[] objectPair : allIdeas){
                if(((User)objectPair[1]).getID() == user.getID())
                    userIdeas.add((Idea)objectPair[0]);
            }
            user.setIdeas(userIdeas);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
    public Idea getIdea(int id){
        Session session = factory.openSession();
        Transaction tx = null;
        Idea idea = null;

        try {
            tx = session.beginTransaction();
            List<Object[]> results = session.createQuery(
                    "FROM Idea AS idea INNER JOIN idea.user INNER JOIN idea.category").list();
            for(Object[] pair : results){
                Idea currentIdea = (Idea)pair[0];
                if(currentIdea.getID() == id) {
                    idea = currentIdea;
                    idea.setUser((User) pair[1]);
                    idea.setCategory((Category) pair[2]);
                    break;
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return idea;
    }
    public List<Idea> getIdeas(){
        Session session = factory.openSession();
        Transaction tx = null;
        List<Idea> ideas = new ArrayList<Idea>();

        try {
            tx = session.beginTransaction();
            List<Object[]> results = session.createQuery(
                    "FROM Idea AS idea INNER JOIN idea.user INNER JOIN idea.category").list();
            for(Object[] pair : results){
                Idea idea = (Idea)pair[0];
                idea.setUser((User) pair[1]);
                idea.setCategory((Category) pair[2]);
                ideas.add(idea);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ideas;
    }
    public List<Category> getCategories(){
        Session session = factory.openSession();
        Transaction tx = null;
        List<Category> categories = null;

        try {
            tx = session.beginTransaction();
            categories = session.createQuery(
                    "FROM Category").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return categories;
    }
    public Integer addIdea(String title, String description, double rating,
                           double targetSum, double currentSum,
                           int userID, Date endDate, int voiceCount,
                           int categoryID, String photoUrl, String videoUrl){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer ideaID = null;
        try {
            tx = session.beginTransaction();
            User user = (User)session.get(User.class, userID);
            Category category = (Category)session.get(Category.class, categoryID);
            Idea idea = new Idea(title, description, rating, targetSum, currentSum,
                    user, endDate, voiceCount, category, photoUrl, videoUrl);
            ideaID = (Integer) session.save(idea);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ideaID;
    }

    public Integer addUser(String nickname, List<Idea> ideas, String firstName,
                           String lastName, String description,
                           Date birthday, String password, String imgUrl, Date joinDate){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer userID = null;
        try {
            tx = session.beginTransaction();
            User user = new User(nickname, ideas, firstName,
                    lastName, description,
                    birthday, password, imgUrl, joinDate);
            userID = (Integer) session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userID;
    }
    */
}
