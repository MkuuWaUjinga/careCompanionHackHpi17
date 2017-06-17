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

  constructor(
    public navCtrl: NavController,
    private http: Http,
    public storage: Storage
  ) {


    Observable.interval(1000).subscribe(x => {
      this.getPaitenInfo();
    });
  }

  getPaitenInfo = () => {

    console.log('Send request');

  }

  addPatient = () => {
    this.navCtrl.push(AddPatientPage, {});
    console.log('click');
  }

  search = () => {
    console.log('click');
  }

}
