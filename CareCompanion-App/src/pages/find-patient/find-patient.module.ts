import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { FindPatientPage } from './find-patient';

@NgModule({
  declarations: [
    FindPatientPage,
  ],
  imports: [
    IonicPageModule.forChild(FindPatientPage),
  ],
  exports: [
    FindPatientPage
  ]
})
export class FindPatientPageModule {}
