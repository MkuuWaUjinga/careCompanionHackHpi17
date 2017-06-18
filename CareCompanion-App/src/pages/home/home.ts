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
  patientData:any;

  constructor(
    public navCtrl: NavController,
    private http: Http,
    public storage: Storage
  ) {

    Observable.interval(5000).subscribe(x => {
      this.getPaitenInfo();
    });

    this.searchActive = false;

    storage.get('patient').then((val) => {
      console.log(val);
      this.patientData = val;
    });

  }

  getPaitenInfo = () => {
    console.log('Send request');
    // https://hpk5wsr3md.execute-api.us-east-1.amazonaws.com/dev/getdata
    this.http.get("https://og7h38hfi6.execute-api.us-east-1.amazonaws.com/dev/getdata")
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

  refreshPatientData = () => {
    this.storage.get('patient').then((val) => {
      console.log(val);
      this.patientData = val;
    });
  }

}
