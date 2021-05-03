import { Component, OnInit } from '@angular/core';
import {CategorieService} from "../_services/categorie.service";
import {Router} from "@angular/router";
import {ArticleService} from "../_services/article.service";
import {Article} from "../_models/article";
import {Categorie} from "../_models/categorie";

@Component({
  selector: 'app-add-article',
  templateUrl: './add-article.component.html',
  styleUrls: ['./add-article.component.css']
})
export class AddArticleComponent implements OnInit {

  public article = {nom: '', prixAchat:0, prixVente:0, quantite:0 ,listOfCategories:null};
  submitted=false;
  listOfCategories:Categorie[];
  categorieIdSelected:number;
  categorieSelected:Categorie;

  constructor(private articleService: ArticleService,private categorieService:CategorieService,private router:Router) { }

  ngOnInit(): void {
    this.getAllCategories();
    this.getCategorieByCode("0000");
  }

  saveArticle() {
    const data = {
      nom: this.article.nom,
      prixAchat: this.article.prixAchat,
      prixVente: this.article.prixVente,
      quantite: this.article.quantite,
      listOfCategories:[
        {
          code:this.categorieSelected.code,
          nom:this.categorieSelected.nom
        }
      ]
    };
    this.articleService.create(data)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
        },
        error => {
          console.log(error);
        });
  }

  /**
   * This method selected one category  from the id
   * @param id
   */
  onCategorieSelected(id){
    this.categorieService.getCategorieById(id)
      .subscribe(
        response => {
          this.categorieSelected=response;
          console.log("categorieSelected");
          console.log(response);
        },
        error => {
          console.log(error);
        });
  }

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

  getCategorieByCode(code){
    this.categorieService.findByCode(code).subscribe(
      data=>{
        this.categorieIdSelected=data.id;
        console.log("categorieSelected: "+data);
      },
      error=>{
        console.log(error);
      }
    );
  }


  newArticle() {
    this.getAllCategories();
    this.submitted = false;
    this.article = {nom: '', prixAchat:0, prixVente:0, quantite:0, listOfCategories:this.getAllCategories()};
  }

  retourArriere(){
    this.router.navigateByUrl("/articles")
  }
}
