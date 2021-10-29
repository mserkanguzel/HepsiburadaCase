package com.android.hepsiburadacase.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.android.hepsiburadacase.databinding.FragmentDetailBinding
import com.android.hepsiburadacase.model.AppsModelResult
import com.android.hepsiburadacase.model.BooksModelResult
import com.android.hepsiburadacase.model.MovieAndMusicModelResult
import com.android.hepsiburadacase.utils.downloadImage
import com.android.hepsiburadacase.utils.placeHolderBuilder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
        private var movieOrMusic : MovieAndMusicModelResult? = null
        private var books : BooksModelResult? = null
        private var apps : AppsModelResult? = null

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            movieOrMusic = args.moviesAndMusicc
            books = args.books
            apps = args.apps
        }
        // kind = "song" -> music
        // kind = 'software' -> apps
        // kind = 'feature-movie' -> movie
        // wrapperType = "audiobook" -> book

        binding.apply {
            // if(movieOrMusic?.kind == "song") bu şekilde movie veya music ayrimi yapilabilir- feature-movie-> movie
         // movie yada müzik geldi
            if(books == null && apps == null) {
                if(movieOrMusic?.collectionName != null) {
                    textViewName.text = movieOrMusic?.collectionName
                }
                else {
                    textViewName.text = movieOrMusic?.trackName
                }
                textViewDescList.text = movieOrMusic?.longDescription
                textViewPrice.text = movieOrMusic?.collectionPrice.toString()
                imageViewDetail.downloadImage(movieOrMusic?.artworkUrl100, placeHolderBuilder(requireContext()))
            }
            // books geldi
            if(movieOrMusic == null && apps == null) {
              if(books?.collectionName != null){
                  textViewName.text = books?.collectionName
              }
                else{
                  textViewName.text = books?.artistName
                }
                  textViewDescList.text = books?.description
                textViewPrice.text = books?.collectionPrice.toString()
                imageViewDetail.downloadImage(books?.artworkUrl100, placeHolderBuilder(requireContext()))
            }
            // apps geldi
            if(movieOrMusic == null && books == null) {
                if(apps?.trackName != null) {
                    textViewName.text = apps?.trackName
                }
                else {
                    textViewName.text = apps?.sellerName
                }
                textViewDescList.text = apps?.description
                textViewPrice.text = apps?.formattedPrice
                imageViewDetail.downloadImage(apps?.artworkUrl100, placeHolderBuilder(requireContext()))
            }

        }

    }
}