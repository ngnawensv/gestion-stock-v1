import {Component, OnInit} from '@angular/core';
import {CategorieService} from "../_services/categorie.service";
import {Categorie} from "../_models/categorie";
import {Router} from "@angular/router";

@Component({
  selector: 'app-categorie',
  templateUrl: './categorie.component.html',
  styleUrls: ['./categorie.component.css']
})
export class CategorieComponent implements OnInit {

  categories:Categorie[];
  currentCategorie = null;
  currentIndex = -1;
  nom = '';
  submited=false;

  constructor(private categorieServie: CategorieService, private router:Router) {}

  ngOnInit(): void {
 // this.getAllCategories();
    /*this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      processing: true
    };*/
  }

  getAllCategories() {
    return this.categorieServie.getAllCategories().subscribe(
      data => {
        this.categories = data;
        console.log(data);
        this.router.navigateByUrl("/categories")
      },
      error => {
        console.log("Erreur: " + error);
      });
  }

  refreshList() {
    this.getAllCategories();
    this.currentCategorie = null;
    this.currentIndex = -1;
  }

  setActiveTutorial(tutorial, index) {
    this.currentCategorie = tutorial;
    this.currentIndex = index;
  }

  removeAllTutorials() {
    this.categorieServie.deleteAll()
      .subscribe(
        response => {
          console.log(response);
          this.getAllCategories();
        },
        error => {
          console.log(error);
        });
  }

  /**
   *
   */
  searchTitle() {
    this.categorieServie.findByTitle(this.nom)
      .subscribe(
        data => {
          this.categories = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  deleteCategorie(id){
    console.log(id)
    this.categorieServie.delete(id).subscribe(
      response => {
        console.log(response);
        this.submited=true;
        this.getAllCategories();
        //this.router.navigate(['/categories']);
      },
      error => {
        console.log(error);
      });
  }

  /**
   * Methode pour afficher le formulaire de mise à jour
   * @param id
   * @param currentCategorie
   */
  editCategorie(currentCategorie) {
   // this.currentCategorie.id=id;
    this.currentCategorie=currentCategorie;
    console.log(this.currentCategorie.id);
    console.log(this.currentCategorie.code);
    console.log(this.currentCategorie.nom)
    //this.router.navigate(['/categories/edit']);
  }

  /**
   * Methode pour mettre a jour un formualire
   * @param id
   * @param currentCategorie
   */
  updateCategorie(currentCategorie) {
    this.categorieServie.update(currentCategorie.id, currentCategorie)
      .subscribe(
        response => {
          console.log(response);
          this.submited=true;
          this.currentCategorie=null;
        },
        error => {
          console.log(error);
        });
  }

  retourArriere(){
    this.currentCategorie=null;
    this.router.navigateByUrl("/categories")
  }


}
