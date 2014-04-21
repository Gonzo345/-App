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

@implementation ViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    // Creamos una coordenada inicial, en nuestro caso perteneciente a Valencia.
    CLLocationCoordinate2D initialLocation;
    initialLocation.latitude = 39.471914;
    initialLocation.longitude= -0.376797;
    
    // Esto situará el centro del mapa en Valencia con la distancia de región que establezcamos.
    MKCoordinateRegion region = MKCoordinateRegionMakeWithDistance(initialLocation, 200, 200);
    
    [self.MV setRegion:region animated:YES];
    
    MV.showsUserLocation=YES;
    MKPointAnnotation *pointAnnotation = [[MKPointAnnotation alloc]init];
    CLLocationCoordinate2D cord= CLLocationCoordinate2DMake(23.050039, 72.56321);
    pointAnnotation.coordinate=cord;
    pointAnnotation.title=@"ASDFASDFASDF";
    pointAnnotation.subtitle=@"ASDFASDFASDF";
    [MV addAnnotation:pointAnnotation];

    
 
    
}


- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
