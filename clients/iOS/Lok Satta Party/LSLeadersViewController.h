//
//  LSLeaders.h
//  Lok Satta Party
//
//  Created by Vimukti 22 on 1/26/14.
//  Copyright (c) 2014 Vimukti 22. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "LSLeader.h"

@interface LSLeadersViewController : UIViewController
{
    UIView *imagesView;
    NSArray *leadersArray;
}
@property(nonatomic,retain) UIView *imagesView;
@property(nonatomic,retain) NSArray *leadersArray;

@end
