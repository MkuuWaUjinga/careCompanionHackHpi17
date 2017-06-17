import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { AddPatientPage } from '../add-patient/add-patient';
import { Http } from '@angular/http';
import { Storage } from '@ionic/storage';
import {Observable} from 'rxjs/Rx';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  searchActive:boolean;
  paitentData:any;

  constructor(
    public navCtrl: NavController,
    private http: Http,
    public storage: Storage
  ) {

    Observable.interval(5000).subscribe(x => {
      this.getPaitenInfo();
    });

    this.searchActive = false;

    storage.get('paitent').then((val) => {
      console.log(val);
      this.paitentData = val;
      console.log(this.paitentData.name);
    });
  }

  getPaitenInfo = () => {
    console.log('Send request');
    // https://hpk5wsr3md.execute-api.us-east-1.amazonaws.com/dev/getdata
    this.http.get("https://hpk5wsr3md.execute-api.us-east-1.amazonaws.com/dev/getdata")
      .subscribe(data => {
        console.log('Response');
        console.log(data);
      }, error => {
        console.log(JSON.stringify(error.json()));
      }
    );
  }

  addPatient = () => {
    this.navCtrl.push(AddPatientPage, {});
    console.log('click');
  }

  search = () => {
    console.log('click');
    if(this.searchActive == false){
      this.searchActive = true;
      console.log(this.searchActive);
    }
    else{
      this.searchActive = false;
      console.log(this.searchActive);
    }
  }

  onInput = () => {
    console.log('search');
  }

  ionCancel = () => {
    console.log('search');
  }

}
