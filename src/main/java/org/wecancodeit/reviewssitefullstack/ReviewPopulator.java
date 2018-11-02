package org.wecancodeit.reviewssitefullstack;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReviewPopulator implements CommandLineRunner {

	@Resource
	ReviewRepository reviewRepo;

	@Resource
	CategoryRepository categoryRepo;


	@Override
	public void run(String... args) throws Exception {
		
		Category tearJerker = new Category("Wistful movies");
		Category romance = new Category("Romantic movies");
		romance = categoryRepo.save(romance);
		tearJerker = categoryRepo.save(tearJerker);

Review gotf = new Review("Grave of the Fireflies", "If you're looking for a dreamy Ghibli world meant to evoke your nostalgia boner, please keep looking. Fantasy and romance are left behind in favor of war and loss and the brutal reality of japan following its defeat in WW2. This movie is not for the emotionally sensitive, but the beautifully woven story may find you intrigued nonetheless. StillCrying/10", tearJerker);
		
		
		Review fivecps = new Review("Five Centimeters per Second", "Five centimeters per second is a love story. The fleeting and ephemeral emotions evoked when your high school sweetheart moves across the coutnry are real in this one. Not every romance story has a happy ending, and you're left wanting more. 9/10", romance);
		
		
		Review arrietty = new Review("Secret World of Arrietty", "Based off the book series 'The Borrowers', this movie follows the tale of a young girl coming to age and her daily life avoiding human 'beans' We find a beautiful world and the miniatue people who inhabit it coupled with a story of adventure and loss. 10/10", tearJerker);
		
		
		Review hmc = new Review("Howl's Moving Castle", "HMC is a story of romance. A quant hat maker meets a mischievous wizard, but her only issue is that she's been cursed. In her quest to rid her curse she falls in love. No ghiblii movie is complete without wonderful art and quintessential world building, and that, coupled with a romance story makes for sentimental movie. This movie invokes a sense of nostalgia, even if it's your first time seeing it. 10/10", romance);
		
		
		Review spiritedAway = new Review("Spirited Away", "A movie about a young girl's adventures through a spiritual world and her quest to get her parents back. Hayao Miyazaki's beautiful world-building matched with a heartfelt story makes this movie a classic watch. 10/10", tearJerker);
		hmc = reviewRepo.save(hmc);
		spiritedAway = reviewRepo.save(spiritedAway);
		
		arrietty = reviewRepo.save(arrietty);
		gotf = reviewRepo.save(gotf);
		fivecps = reviewRepo.save(fivecps);

		
		
		
	}
}
