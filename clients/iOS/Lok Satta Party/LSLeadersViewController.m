//
//  LSLeaders.m
//  Lok Satta Party
//
//  Created by Vimukti 22 on 1/26/14.
//  Copyright (c) 2014 Vimukti 22. All rights reserved.
//

#import "LSLeadersViewController.h"

@implementation LSLeadersViewController
@synthesize imagesView,leadersArray;

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}
-(void) viewDidLoad{
    [super viewDidLoad];
    self.title = @"LSP";
    
    UIView *mainView =[[UIView alloc]initWithFrame:self.view.bounds];
    self.view=mainView;
    
    UIView *view1=[[UIView alloc]init];
    
    UITextField *stateField=[[UITextField alloc]initWithFrame:CGRectMake(0, 50, 100, 50)];
    [stateField setText:@"State"];
    
    UITextField *contstitutionField=[[UITextField alloc]initWithFrame:CGRectMake(0, 120, 100, 50)];
    
    [contstitutionField setText:@"Constitution"];
    
    [view1 addSubview:stateField];
    [view1 addSubview:contstitutionField];
    
    // leaders details.
    leadersArray = [NSArray alloc];
    [self addImagesView:leadersArray];
    
    [self.view addSubview:view1];
    [self.view addSubview:imagesView];
    
}

-(void)addImagesView:(NSArray *)leaders{
    int tag=0;
    for (LSLeader *leader in leaders) {
        
        UITapGestureRecognizer *singleTap=[[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(imageTaped:)];
        singleTap.numberOfTapsRequired=1;
        singleTap.numberOfTouchesRequired=1;
        
        UIImageView *image = [[UIImageView alloc]initWithImage:[UIImage imageNamed:leader.imageName]];
        image.userInteractionEnabled=YES;
        image.backgroundColor=[UIColor clearColor];
        image.tag=tag++;
        [image addGestureRecognizer:singleTap];
        
        
        [self.imagesView addSubview:image];
        
    }
}


-(void)imageTaped: (UITapGestureRecognizer *)recognizer
{
    NSLog(@"single Tap on imageview");
    UIImageView *selectedTextView = (UIImageView *)recognizer.view;
    NSInteger tag=selectedTextView.tag;
    LSLeader *leader=[leadersArray objectAtIndex:tag];
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect
{
    // Drawing code
}
*/

@end
