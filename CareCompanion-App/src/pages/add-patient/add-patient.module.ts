import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { AddPatientPage } from './add-patient';

@NgModule({
  declarations: [
    AddPatientPage,
  ],
  imports: [
    IonicPageModule.forChild(AddPatientPage),
  ],
  exports: [
    AddPatientPage
  ]
})
export class AddPatientPageModule {}
