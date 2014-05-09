//
//  ViewController.m
//  SineDoloreSVM
//
//  Created by Aitor Costa Jiménez on 16/04/14.
//  Copyright (c) 2014 SineDolore. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

//definimos el punto medio de la vista
#define latit 39.471914;
#define longi -0.376797;

//Span
#define spanvar 0.01f;

@implementation ViewController;
@synthesize MV;

- (void)viewDidLoad
{
    [super viewDidLoad];
    //Create the Region
    MKCoordinateRegion myregion;
    
    // center
    CLLocationCoordinate2D center;
    center.latitude = latit;
    center.longitude= longi;

    //span
    MKCoordinateSpan span;
    span.latitudeDelta = spanvar;
    span.longitudeDelta = spanvar;
    
    myregion.center=center;
    myregion.span = span;
    
    //set our mapview
    [MV setRegion: myregion animated:YES];
    
    
    //Anotation
  
    
    
}


- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
