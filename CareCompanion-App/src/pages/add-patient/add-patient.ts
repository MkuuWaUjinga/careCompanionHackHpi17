import { IonicPage, NavController, NavParams } from 'ionic-angular';
import {Component, Input} from '@angular/core';
import { Http } from '@angular/http';
import { Storage } from '@ionic/storage';

@IonicPage()
@Component({
  selector: 'page-add-patient',
  templateUrl: 'add-patient.html',
})
export class AddPatientPage {

  myInputName: any;
  myInputStreet: any;
  myInputZip: any;
  myInputCity: any;
  myInputCountry: any;
  myInputDiseases: any;

  constructor(
    public navCtrl: NavController,
    public navParams: NavParams,
    public storage: Storage
  ) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad AddPatientPage');
  }

  send = () => {
    this.storage.set('paitent', {
      "name" : this.myInputName,
      "street" : this.myInputStreet,
      "zip" : this.myInputZip,
      "city" : this.myInputCity,
      "country" : this.myInputCountry,
      "diseases" : this.myInputDiseases
    });
    console.log('Form was submitted');
    console.log(this.myInputName);
  }



}
