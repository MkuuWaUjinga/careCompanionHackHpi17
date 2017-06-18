import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { RateYourDayPage } from './rate-your-day';

@NgModule({
  declarations: [
    RateYourDayPage,
  ],
  imports: [
    IonicPageModule.forChild(RateYourDayPage),
  ],
  exports: [
    RateYourDayPage
  ]
})
export class RateYourDayPageModule {}
