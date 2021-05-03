package cm.belrose.stockserveur.config.initializer;

public interface IInitializeStockDatabase {
    public void initCategorie();

    public void initArticleCategorie();

    public void initArticle();

    public void initVente();

    public void initImage();

    public void initArticleVente();

   public void initRole();

    //public void initUsers();

    public void initPersonne();

    public void initCommandeClient();

    public void initLivraisonFournisseur();


    public void initMouvementStock();
}
