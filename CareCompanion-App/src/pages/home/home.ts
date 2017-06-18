import { Component, ViewChild } from '@angular/core';
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

  @ViewChild('barCanvas') barCanvas;
  barChart: any;

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






  // ##################
  // lineChart
  public lineChartData:Array<any> = [
    {data: [18, 48, 77, 9, 40, 27, 40], label: 'Heart rate'}
  ];
  public lineChartLabels:Array<any> = ['6', '5', '4', '3', '2', '1', '0'];
  public lineChartOptions:any = {
    scales: {
                       yAxes: [{
                                display: true,
                                stacked: true,
                                ticks: {
                                    min: 0 ,// minimum value
                                    max: 150, // maximum value
                                    stepSize : 25
                                }
                       }]
                    }
  };
  public lineChartColors:Array<any> = [
    { // grey
      backgroundColor: 'rgba(148,159,177,0.2)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    }
  ];
  public lineChartLegend:boolean = false;
  public lineChartType:string = 'line';

  public randomize():void {
    let _lineChartData:Array<any> = new Array(this.lineChartData.length);
    for (let i = 0; i < this.lineChartData.length; i++) {
      _lineChartData[i] = {data: new Array(this.lineChartData[i].data.length), label: this.lineChartData[i].label};
      for (let j = 0; j < this.lineChartData[i].data.length; j++) {
        _lineChartData[i].data[j] = Math.floor((Math.random() * 100) + 1);
      }
    }
    this.lineChartData = _lineChartData;
  }

  // events
  public chartClicked(e:any):void {
    console.log(e);
  }

  public chartHovered(e:any):void {
    console.log(e);
  }


}
