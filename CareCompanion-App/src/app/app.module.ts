import { NgModule, ErrorHandler } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';

import { AboutPage } from '../pages/about/about';
import { ContactPage } from '../pages/contact/contact';
import { HomePage } from '../pages/home/home';
import { GesundheitscloudPage } from '../pages/gesundheitscloud/gesundheitscloud';
import { RateYourDayPage } from '../pages/rate-your-day/rate-your-day';
import { ReliveStressPage } from '../pages/relive-stress/relive-stress';
import { AddPatientPage } from '../pages/add-patient/add-patient';
import { FindPatientPage } from '../pages/find-patient/find-patient';

import { TabsPage } from '../pages/tabs/tabs';

import { ChartsModule } from 'ng2-charts/ng2-charts';
import '../../node_modules/chart.js/dist/Chart.bundle.min.js';

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { HttpModule } from '@angular/http';
import { IonicStorageModule } from '@ionic/storage';
import { Geolocation } from '@ionic-native/geolocation';
import { AgmCoreModule } from '@agm/core';

@NgModule({
  declarations: [
    MyApp,
    AboutPage,
    ContactPage,
    HomePage,
    AddPatientPage,
    FindPatientPage,
    GesundheitscloudPage,
    RateYourDayPage,
    ReliveStressPage,
    TabsPage
  ],
  imports: [
    BrowserModule,
    HttpModule,
    ChartsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCOp5hcj-xcekPbzy5OXbE2WDxouPS9ZZY'
    }),
    IonicStorageModule.forRoot(),
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    AboutPage,
    ContactPage,
    HomePage,
    AddPatientPage,
    FindPatientPage,
    GesundheitscloudPage,
    RateYourDayPage,
    ReliveStressPage,
    TabsPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    Geolocation,
    {provide: ErrorHandler, useClass: IonicErrorHandler}
  ]
})
export class AppModule {}
