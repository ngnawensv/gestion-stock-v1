import { Component, OnInit } from '@angular/core';
import {Categorie} from "../_models/categorie";
import {CategorieService} from "../_services/categorie.service";
import {Router} from "@angular/router";
import {Article} from "../_models/article";
import {ArticleService} from "../_services/article.service";

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css']
})
export class ArticleComponent implements OnInit {

  articles:Article[];
  article_details:Article=null;
  listCategorieOfArticle:Categorie[];
  currentArticle = null;
  currentIndex = -1;
  nom = '';
  submited=false;
  listOfCategories:Categorie[];
  categorieSelected:Categorie;
  categorieIdSelected:number;

  constructor(private articleservice: ArticleService,
              private categorieService:CategorieService,
              private router:Router) {}

  ngOnInit(): void {
     //this.getAllCategories();
    /*this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      processing: true
    };*/
  }

  getAllArticles() {
    return this.articleservice.getAllArticles().subscribe(
      data => {
        this.articles = data;
        this.article_details=null;
        console.log(data);
        this.router.navigateByUrl("/articles")
      },
      error => {
        console.log("Erreur: " + error);
      });
  }

  /*getById(id) {
    return this.articleservice.getById(id).subscribe(
      data => {
        this.currentArticle = data;
        this.articles.push(this.currentArticle);
        console.log(data);
        //this.router.navigateByUrl("/articles")
      },
      error => {
        console.log("Erreur: " + error);
      });
  }*/

  onCategorieSelected(id){
    this.categorieService.getCategorieById(id)
      .subscribe(
        data => {
          this.categorieSelected=data;
          console.log("categorieSelected");
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  /*getCategorieByCode(){
        this.categorieIdSelected=data.id;
  }*/

  getAllCategories(){
    this.categorieService.getAllCategories().subscribe(
      data=>{
        this.listOfCategories=data;
        console.log(data);
      },
      error=>{
        console.log(error);
      }
    );
  }

  refreshList() {
    this.getAllArticles();
    this.currentArticle = null;
    this.currentIndex = -1;
  }

  setActiveTutorial(tutorial, index) {
    this.currentArticle = tutorial;
    this.currentIndex = index;
  }

  removeAllTutorials() {
    this.articleservice.deleteAll()
      .subscribe(
        response => {
          console.log(response);
          this.getAllArticles();
        },
        error => {
          console.log(error);
        });
  }

  /**
   *
   */
  searchTitle() {
    this.articleservice.findByKeyWord(this.nom)
      .subscribe(
        data => {
          this.articles = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  deleteArticle(id){
    console.log(id)
    this.articleservice.delete(id).subscribe(
      response => {
        console.log(response);
        this.submited=true;
        this.getAllArticles();
        //this.router.navigate(['/categories']);
      },
      error => {
        console.log(error);
      });
  }

  /**
   * Methode pour afficher le formulaire de mise ?? jour
   * @param id
   * @param currentCategorie
   */
  editArticle(curentArticle) {
    // this.currentCategorie.id=id;
    this.currentArticle=curentArticle;
    console.log(this.currentArticle.id);
    console.log(this.currentArticle.nom);
    console.log(this.currentArticle.prixAchat);
    console.log(this.currentArticle.prixVente);
    console.log(this.currentArticle.quantite)
    //this.router.navigate(['/categories/edit']);
  }

  /**
   * Methode pour mettre a jour un formualaire
   * @param id
   * @param currentArticle
   */
  updateArticle(currentArticle) {
    this.articleservice.update(currentArticle.id, currentArticle)
      .subscribe(
        data => {
          console.log(data);
          this.submited=true;
          this.currentArticle=null;
          this.article_details=null;
        },
        error => {
          console.log(error);
        });
  }

  detailsArticle(currentArticle:Article){
    this.article_details=currentArticle;
    this.listCategorieOfArticle=this.article_details.listOfCategories;
    this.articles=[];
    this.articles.push(this.article_details);
    console.log(this.article_details);
  }

  retourArriereOnDetails(){
  }


  retourArriere(){
    this.currentArticle=null;
    this.router.navigateByUrl("/articles")
  }



}
