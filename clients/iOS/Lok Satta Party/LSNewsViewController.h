//
//  LSNews.h
//  Lok Satta Party
//
//  Created by Vimukti 22 on 1/26/14.
//  Copyright (c) 2014 Vimukti 22. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "LSNewsFeed.h"

@interface LSNewsViewController : UIViewController<UITableViewDataSource,UITableViewDelegate>
{
    UIImageView *customImage;
}
@property(nonatomic,retain)UIImageView *customImage;

@end
