import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { ReliveStressPage } from './relive-stress';

@NgModule({
  declarations: [
    ReliveStressPage,
  ],
  imports: [
    IonicPageModule.forChild(ReliveStressPage),
  ],
  exports: [
    ReliveStressPage
  ]
})
export class ReliveStressPageModule {}
